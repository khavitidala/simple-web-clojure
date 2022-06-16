(ns simple-web-clojure.handler
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [api-defaults wrap-defaults]]
            [simple-web-clojure.view :as view]
            [ring.middleware.session :refer [wrap-session]]
            [ring.middleware.params :refer [wrap-params]]))

(defroutes app-routes
  (GET "/" [] view/index)
  (POST "/" [] view/form-handle)
  (GET "/del" [] view/delete-sess)
  (route/not-found "Not Found"))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(def app
  (-> app-routes
      (wrap-defaults (assoc api-defaults :anti-forgery true))
      (wrap-session {:cookie-attrs {:max-age 3600}})
      wrap-params))