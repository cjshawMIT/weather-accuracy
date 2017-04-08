(ns weather.models.daily_weather
  (:require [clojure.java.jdbc :as sql]))

(def spec (or (System/getenv "DATABASE_URL")
              "postgresql://localhost:5432/weather_accuracy"))

(defn all []
  (into [] (sql/query spec ["select * from daily_weather order by id date"])))

(defn create [daily-report]
  (sql/insert! spec :daily_weather [:body] [daily-report]))
