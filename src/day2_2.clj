(ns day2_2
  (:require [aoc-2023.core :refer [parse-lines]]
            [clojure.string :as str]))

; processing today's specific input file
(defn parse-cubes [string]
  (let [[number color] (str/split string #" ")] [(keyword color) (parse-long number)]))
(defn parse-draw [string]
  (into {} (map parse-cubes (str/split string #", "))))
(defn parse-game [string]
  (map parse-draw (str/split string #"; ")))
(def input
  (into {} (map vec (parse-lines "resources/day2.txt" #"Game (\d+): (.*)" [parse-long parse-game]))))

; logic to calculate the largest number of cubes seen of each color

(defn min-cubes [game]
  (apply (partial merge-with max) game))

; part 2 solution
(reduce + (map #(reduce * (vals (min-cubes %))) (vals input)))
