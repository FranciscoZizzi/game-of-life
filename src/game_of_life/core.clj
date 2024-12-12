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

