(ns jwt-verify-jwks.core
  (:require [buddy.core.keys :as keys]
            [buddy.sign.jwt :as jwt]
            [cheshire.core :as json]
            [clj-crypto.core :as crypto]
            [clj-http.client :as client]
            [clojure.string :as str]
            [compojure.core :refer :all]
            [org.httpkit.server :refer [run-server]]))


(defn- extract-jwt
  [jwks jwt]
  (let [kid (get-in jwt [:headers "kid"])]
    (first (filter #(= (:kid %) kid) (:keys jwks)))))


(defn- get-jwks
  [url]
  (try
    (json/parse-string (get-in (client/get url) [:body]) true)
    (catch Exception e (str "Error with the JWKS: " (.getMessage e)))))


(defn- build-pem
  [jwt jwks-url]
  (let [jwks (get-jwks jwks-url)]
    (extract-jwt jwks jwt)))


(defn- base64-decode
  [to-decode]
  (apply str (map char (crypto/decode-base64 to-decode))))


(defn- decode-jwt
  [jwt]
  (try
    (let [[headers payload signature] (str/split jwt #"\.")]
      (when (and headers payload signature)
        {:headers   (json/decode (base64-decode headers))
         :payload   (json/decode (base64-decode payload))
         :signature signature}))
    (catch Exception e (str "Error decoding JWT: " (.getMessage e)))))


(defn- validate-jwt
  [jwt jwks-url alg]
  (try
    (let [decoded-jwt (decode-jwt jwt)
          pem (build-pem decoded-jwt jwks-url)
          public-key (keys/jwk->public-key pem)]
      (when (keys/public-key? public-key)
        (jwt/unsign jwt public-key {:alg (keyword alg)})))
    (catch Exception e (str "Error with public key: " (.getMessage e)))))


(defn jwt-validate-jwks
  [jwt jwks-url alg]
  (validate-jwt jwt jwks-url alg))