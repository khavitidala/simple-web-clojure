(ns simple-web-clojure.handler
  (:require [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (route/not-found "Not Found"))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(def app
  (wrap-defaults app-routes site-defaults))
