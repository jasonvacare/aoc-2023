(ns day2_1
  (:require [aoc-2023.core :refer [parse-lines]]
            [clojure.string :as str]))

; processing today's input file
(defn parse-cubes [string]
  (let [[number color] (str/split string #" ")] [(keyword color) (parse-long number)]))
(defn parse-draw [string]
  (into {} (map parse-cubes (str/split string #", "))))
(defn parse-game [string]
  (map parse-draw (str/split string #"; ")))
(def input
  (into {} (map vec (parse-lines "resources/day2.txt" #"Game (\d+): (.*)" [parse-long parse-game]))))

; logic to detect games playable with specified bag
(def bag
  {:red 12 :green 13 :blue 14})
(defn possible-draw? [draw]
  (every? (fn [[color number]] (<= number (get bag color 0))) draw))
(defn possible-game? [game]
  (every? possible-draw? game))

; part 1 solution
(reduce + (map first (filter #(possible-game? (second %)) input)))