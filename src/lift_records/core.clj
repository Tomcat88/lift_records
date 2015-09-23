(ns lift-records.core
  (:gen-class)
  (:require [lift-records.db :as db]
            [clj-time.core :as t]
            [clj-time.format :as format]))

(defn prepare-add
  [records [name setreps weight]]
  (let [[_ set reps] (re-find db/pattern setreps)
        formatter (format/formatter "dd/MM/yyyy")
        date (format/unparse formatter (t/now))
        w (read-string weight)]
    ;;(println records name set reps weight w)
    (comp db/save-db 
          (partial db/set-new-record records name set reps {:weight w :date date}))))

(defn prepare-record
  [records [name setreps]]
  (let [[_ set reps] (re-find db/pattern setreps)]
    (comp 
     (partial println "Max for " name " " setreps "is : ")
     (partial get-in records [name set reps "max"]))))

(defn prepare-story
  [records [name setreps]]
  (let [[_ set reps] (re-find db/pattern setreps)]
    (fn [] 
      (doseq [{w "weight" d "date"} (get-in records [name set reps "records"])]
        (println d "->" w "kg")))))

(defn -main
  [op & args]
  (let [records (db/load-db)
        f (condp = op
          "add" (prepare-add records args)
          "max" (prepare-record records args)
          "story" (prepare-story records args)
          #(println "Action unknown!"))]
    ;;(println records (f))
    (f)
    ))











