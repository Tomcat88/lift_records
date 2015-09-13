(ns lift-records.db
  (:gen-class)
  (:require [clojure.data.json :as json]
            [clojure.java.io :as io]
            [me.raynes.fs :as fs :only expand-home]))

(def db-path (fs/expand-home "~/.lift_records/db.json"))
(def pattern #"(\d+)x(\d+)")

(defmacro db-keys [last]
  (vector 'name 'set 'reps last))

(defn load-db
  []
  (if-let [db (and (.exists (io/as-file db-path)) (slurp db-path))]
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

(defn get-records
  [db name set reps]
  (get-in db [name set reps :records]))


(defn assoc-in-with 
  [f map keys val default]
  (if-let [old (apply get-in (into [map] [keys]))]
    (assoc-in map keys (f old val))
    (assoc-in map keys (f default val))))

(defn set-new-record
  [db name set reps {w :weight date :date :as record}]
  (let [updated (assoc-in-with conj db (db-keys :records) record [])]
    (assoc-in-with #(if (> %1 %2) %1 %2) updated (db-keys :max) w 0)))


















