(ns day1-1
  (:require [aoc-2023.core :refer]
            [clojure.string :as str]))

; processing today's input file
(defn input [filename] (str/split-lines (slurp filename)))

; logic to get first and last digit in each line
(defn get-number [string]
  (read-string (str (first (re-seq #"\d" string)) (last (re-seq #"\d" string)))))

; part 1 solution
(reduce + (map #(get-number %) (input "resources/day1.txt")))







