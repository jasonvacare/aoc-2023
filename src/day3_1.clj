(ns day3_1
  (:require [clojure.string :as str]))

(def input
  (str/split-lines (slurp "resources/day3.txt")))

(defn re-seq-part [string line-number]
  (let [match (re-matcher #"(\d+)" string)]
    ((fn step []
       (when (. match find)
         (cons {:line-number line-number :index (. match start) :length (- (. match end) (. match start)) :end (. match end) :part-number (parse-long (. match group))}
               (lazy-seq (step))))))))

(defn re-seq-symbol [string line-number]
  (let [match (re-matcher #"[^\.0-9]" string)]
    ((fn step []
       (when (. match find)
         (cons {:line-number line-number :index (. match start)}
               (lazy-seq (step))))))))

; parse out a list of potential parts and symbols
(def possible-parts (reduce into #{} (map-indexed (fn [index value] (re-seq-part value index)) input)))
(def symbols (reduce into #{} (map-indexed (fn [index value] (re-seq-symbol value index)) input)))

; index-1, start through end-1
; index, start-1, end
; index+1, start through end-1

(def test-part {:line-number 35 :index 103 :length 3 :end 106 :part-number 357})




