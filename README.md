# jwt-verify-jwks

_Validate a JWT based on a JWKS url_

## Installation

[![Clojars Project](https://img.shields.io/clojars/v/jwt-verify-jwks.svg)](https://clojars.org/jwt-verify-jwks)

## Usage

Function requires:
* A string containing the JWT
* A string containing the url of the JWKS
* A string containing the algorithm used to sign the JWT (e.g: 'rs256')
Returns a the unsigned JWT or an error.

```
(ns jwt-verify-jwks.test
  (:require [jwt-verify-jwks.core :refer [jwt-validate-jwks]]))
  
(jwt-validate-jwks "xxxx.xxxx.xxxxx" "http://myjwks.com" "rs256")
;; => {:email "xxx@xxx.com",
       :aud "xxx",
       :sub "xxx",
       :iss "https://xxx.com/",
       :name "xxx@xxx.com",
       :nickname "xxx",
       :exp 1111111111,
       :email_verified true,
       :updated_at "xxxx-xx-xxTxx:xx:xx.xxxZ",
       :picture "https://xxx.com/xxx.png",
       :iat 1111111111}

;; "Error with public key: Token is expired (1111111111)"
```

## Supported algorithms

This library relies on Funcool [buddy-sign](https://funcool.github.io/buddy-sign/latest/) library.
Here are the supported algorithms:

| Algorithm name | Hash algorithms | Keywords |
|---|---|---|
|Elliptic Curve DSA|sha256, sha512|:es256, :es512|
|RSASSA PSS|sha256, sha512|:ps256, :ps512|
|RSASSA PKCS1 v1_5|sha256, sha512|:rs256, :rs512|
|HMAC|sha256*, sha512|:hs256, :hs512|

## [Changelog](https://github.com/fnzc/jwt-verify-jwks/blob/master/CHANGELOG.md)

## Contributing

Pull requests are welcome.

## [License](https://github.com/fnzc/jwt-verify-jwks/blob/master/LICENSE)
