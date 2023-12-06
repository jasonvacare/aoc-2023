(ns day3_1
  (:require [clojure.string :as str]
            [clojure.set :refer [union]]))

(defn re-seq-pos [pattern string index]
  (let [match (re-matcher pattern string)]
    ((fn step []
       (when (. match find)
         (cons {:start (. match start) :end (. match end) :group (. match group) :index index}
           (lazy-seq (step))))))))

(def input
  (str/split-lines (slurp "resources/day3.txt")))

(map-indexed (fn [index value] (re-seq-pos #"(\d+)" value index)) input)
(first (map-indexed (fn [index value] (re-seq-pos #"(\d+)" value index)) input))
(second (map-indexed (fn [index value] (re-seq-pos #"(\d+)" value index)) input))
(into #{} (first (map-indexed (fn [index value] (re-seq-pos #"(\d+)" value index)) input)))
(into #{} (second (map-indexed (fn [index value] (re-seq-pos #"(\d+)" value index)) input)))
(union 
 (into #{} (first (map-indexed (fn [index value] (re-seq-pos #"(\d+)" value index)) input)))
 (into #{} (second (map-indexed (fn [index value] (re-seq-pos #"(\d+)" value index)) input))))



; (def possible-parts (map-indexed (fn [index value] (re-seq-pos #"(\d+)" value index)) input))
; (def symbols (map-indexed (fn [index value] (re-seq-pos #"[^\.0-9]" value index)) input))



