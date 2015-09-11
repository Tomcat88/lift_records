(defproject lift_records "0.1.0"
  :description "A tool that stores weight lifted record given an exercise, a number of sets and a number of reps"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/data.json "0.2.6"]]
  :main ^:skip-aot lift-records.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
