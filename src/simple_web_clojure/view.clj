(ns simple-web-clojure.view
  (:require [hiccup.form :as f]
            [hiccup.core :refer [html]]
            [ring.util.response :as response]))

(defn template
  [teks]
  [:div {:style "border: solid 1px; padding: 5px"} [:center teks]]
  )

(def input-form
  (html [:a {:href "/del"} "Reset"] 
        [:p] 
        (f/form-to [:post ""]
             (f/text-field :content)
             (f/submit-button :submit))))

(defn input-form-sess
  [content session]
  {:body (html [:a {:href "/del"} "Reset"]
               [:p]
               (f/form-to [:post ""]
                          (f/text-field :content)
                          (f/submit-button :submit))
               (for [text content]
                 (template text)))
   :headers {"Content-Type" "text/html"}
   :status 200
   :session session})

(defn store-data
  [params session]
  (let [cntn (:content session)]
    (if (params "content")
      (if (:content session)
        (assoc session :content (conj cntn (params "content")))
        (assoc session :content (conj [] (params "content"))))
      session)))

(defn form-handle
  [{session :session params :form-params}]
  (let [session (store-data params session)
        content (:content session)]
    (if content
      (input-form-sess content session)
      input-form)))


#_{:clj-kondo/ignore [:unused-binding]}
(defn delete-sess
  [{session :session}]
  (-> (response/redirect "/")
      (assoc :session {})))

#_{:clj-kondo/ignore [:unused-binding]}
(defn index [{session :session}] 
  input-form)