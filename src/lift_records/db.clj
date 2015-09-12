(ns lift-records.db
  (:gen-class)
  (:require [clojure.data.json :as json])
  (:require [clojure.java.io :as io]))

(def db-path "~/.lift_records/db.json")
(def pattern #"(\d+)x(\d+)")

(defn load-db
  []
  (if-let [db (slurp db-path)]
    (json/read-str db)
    (do (io/make-parents db-path)
        (spit db-path "{}")
        {})))

(defn save-db
  [db]
  (spit db-path (json/write-str db))
  db)

(defn get-max
  [db name set reps]
  (get-in db [name set reps :max]))

(defn set-new-record
  [db name set reps {w :weight date :date :as record}]
  (let [updated (assoc-in-with conj db (db-keys :records) record [])]
    (assoc-in-with #(if (> %1 %2) %1 %2) updated (db-keys :max) w 0)))

(defn assoc-in-with 
  [f map keys val default]
  (if-let [old (apply get-in (into [map] [keys]))]
    (assoc-in map keys (f old val))
    (assoc-in map keys (f default val))))

(defmacro db-keys [last]
  (vector 'name 'set 'reps last))

















