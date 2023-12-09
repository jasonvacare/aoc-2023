(ns day3_1
  (:require [clojure.string :as str]))

(def input
  (str/split-lines (slurp "resources/day3.txt")))

(defn re-seq-part [string line-number]
  (let [match (re-matcher #"(\d+)" string)]
    ((fn step []
       (when (. match find)
         (cons {:line-number line-number :start (. match start) :length (- (. match end) (. match start)) :end (. match end) :part-number (parse-long (. match group))}
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

(def test-part {:line-number 35 :start 103 :length 3 :end 106 :part-number 357})

(defn get-neighbors [possible-part]
  (set {{set {:line-number (dec (possible-part :line-number)) :index (possible-part :start)}} 
        {set {:line-number (dec (possible-part :line-number)) :index (+ 1 (possible-part :start))}}
        {set {:line-number (dec (possible-part :line-number)) :index (+ 2 (possible-part :start))}}
        {set {:line-number (possible-part :line-number) :index (dec (possible-part :start))}}
        {set {:line-number (possible-part :line-number) :index (possible-part :end)}}
        {set {:line-number (inc (possible-part :line-number)) :index (possible-part :start)}}
        {set {:line-number (inc (possible-part :line-number)) :index (+ 1 (possible-part :start))}}
        {set {:line-number (inc (possible-part :line-number)) :index (+ 2 (possible-part :start))}}}))
