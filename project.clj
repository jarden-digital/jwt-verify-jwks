(defproject jwt-verify-jwks "1.0.1"
  :description "Validate a JWT based on a JWKS url"
  :url "https://github.com/fnzc/jwt-verify-jwks"
  :author "Jeremy Farnault"
  :license {:name "MIT License"
            :url "https://github.com/fnzc/jwt-verify-jwks/blob/master/LICENSE"}
  :dependencies [[buddy/buddy-core "1.5.0"]
                 [buddy/buddy-sign "3.0.0"]
                 [clj-crypto "1.0.2" :exclusions [org.bouncycastle/bcprov-jdk15on]]
                 [clj-http "3.9.1"]
                 [compojure "1.6.0"]
                 [org.clojure/clojure "1.8.0"]]
  :target-path "target/%s"
  :signing {:gpg-key "02EEBEC3BBA039DDBE31A35C85759108682528DE"}
  :source-paths ["src"]
  :test-paths ["spec"]
  :deploy-repositories [["releases" :clojars]]
  :profiles {:uberjar {:aot :all}})
