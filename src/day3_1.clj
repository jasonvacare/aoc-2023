(ns day3_1
  (:require [clojure.string :as str]))

(defn re-seq-pos [pattern string]
  (let [match (re-matcher pattern string)]
    ((fn step []
       (when (. match find)
         (cons {:start (. match start) :end (. match end) :group (. match group)}
           (lazy-seq (step))))))))

(def input
  (str/split-lines (slurp "resources/day3.txt")))

(def possible-parts (map #(re-seq-pos #"(\d+)" %) input))

(def symbols (map #(re-seq-pos #"[^\.0-9]" %) input))



