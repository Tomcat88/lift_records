(ns lift-records.db
  (:gen-class)
  (:require [clojure.data.json :as json])
  (:require [clojure.java.io :as io]))

(def db-path "~/.lift_records/db.json")

(defn load-db
  []
  (if-let [db (slurp db-path)]
    (json/read-str db)
    (do (io/make-parents db-path)
        (spit db-path "{}")
        {})))

(defn save-db
  [db]
  (spit db-path db)
  db)












