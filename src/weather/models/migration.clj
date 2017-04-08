(ns weather.models.migration
  (:require [clojure.java.jdbc :as sql]
            [weather.models.daily_weather :as daily]))

(defn migrated? []
  (-> (sql/query daily/spec
                 [(str "select count(*) from information_schema.tables "
                       "where table_name='daily_weather'")])
      first :count pos?))

(defn migrate []
  (when (not (migrated?))
    (print "Creating database structure...") (flush)
    (sql/db-do-commands daily/spec
                        (sql/create-table-ddl
                         :daily_weather
                         [[:id :serial "PRIMARY KEY"]
                          [:date :date "NOT NULL"]
                          [:high_temp :real]
                          [:low_temp :real]
                          [:one_day_forecast_high_temp :real]
                          [:one_day_forecast_low_temp :real]
                          [:two_day_forecast_high_temp :real]
                          [:two_day_forecast_low_temp :real]
                          [:three_day_forecast_high_temp :real]
                          [:three_day_forecast_low_temp :real]
                          [:created_at :timestamp
                           "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"]]))
    (println " done")))
