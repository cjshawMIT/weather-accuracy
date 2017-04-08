(ns weather.core 
    (:require [compojure.core :refer [defroutes GET]]
              [ring.adapter.jetty :as ring])
    (:gen-class))
(defroutes routes
  (GET "/" [] "<h2>Foo!</h2>"))

(defn -main
"simple app to track and display weather forecast accuracy"
[& args]
  (ring/run-jetty #'routes {:port 8080 :join? false}))
