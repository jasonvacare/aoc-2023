(ns aoc-2023.core
  (:require [clojure.string :as str]))

(defn get-lines [filename]
  (str/split-lines (slurp filename)))
(defn parse [string regex transforms]
  (map #(%1 %2) transforms (rest (re-matches regex string))))
(defn parse-lines [filename regex transforms]
  (map #(parse % regex transforms) (get-lines filename)))