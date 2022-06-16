(defproject simple-web-clojure "0.0.1"
  :description "A super simple web app using clojure"
  :url "https://github.com/khavitidala/simple-web-clojure"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.3"]
                 [hiccup "1.0.5"]]
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler simple-web-clojure.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}})
