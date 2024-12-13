(ns game-of-life.core
  (:gen-class)
  (:require [game-of-life.utils :as utils]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn create-board 
  [width height]
  {:width width :height height :cells #{}})

(defn count-live-neighbors
  [board position]
  (count (filter #(contains? (:cells board) %) (utils/neighbors board position))))

(defn lives-next-tick?
  [board position]
  (let [live-neighbors (count-live-neighbors board position)
        alive (contains? (:cells board) position)]
    (cond
      alive (cond
              (< 2 live-neighbors) false
              (<= 3 live-neighbors) true
              (> 3 live-neighbors) false)
      (= 3 live-neighbors) true
      :else false)))


