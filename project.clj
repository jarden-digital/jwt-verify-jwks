(defproject jwt-verify-jwks "1.0.3"
  :description "Validate a JWT based on a JWKS url"
  :url "https://github.com/fnzc/jwt-verify-jwks"
  :author "Jeremy Farnault"
  :license {:name "MIT License"
            :url "https://github.com/fnzc/jwt-verify-jwks/blob/master/LICENSE"}

  :dependencies [[org.clojure/clojure "1.10.1"]
                 [buddy/buddy-core "1.6.0"]
                 [buddy/buddy-sign "3.1.0"]
                 [clj-crypto "1.0.2" :exclusions [org.bouncycastle/bcprov-jdk15on]]
                 [clj-http "3.10.0"]]

  :min-lein-version "2.0.0"

  :source-paths ["src"]

  :deploy-repositories [["releases" :clojars]]
  :target-path "target/%s"
  :signing {:gpg-key "02EEBEC3BBA039DDBE31A35C85759108682528DE"}
  :profiles {:uberjar {:aot :all}})
