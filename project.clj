(defproject weather-accuracy "0.0.1-alpha"
  :description "Reporting weather forecast accuracy"
  :url "https://github.com/cjshawMIT/weather-accuracy"
  :license {:name "MIT License"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/java.jdbc "0.6.1"]
                 [org.postgresql/postgresql "9.4-1201-jdbc41"]
                 [ring/ring-jetty-adapter "1.5.1"]
                 [compojure "1.5.2"]
                 [ring/ring-defaults "0.1.2"]
                 [ring/ring-json "0.4.0"]
                 [hiccup "1.0.5"]]
  :main ^:skip-aot weather.core
  :uberjar-name "weather-accuracy-standalone.jar"
  :plugins [[lein-ring "0.8.13"]]
  :ring {:handler weather.core/main
         :init weather.models.migration/migrate}
  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [ring-mock "0.1.5"]]}
             :uberjar {:aot :all}})
