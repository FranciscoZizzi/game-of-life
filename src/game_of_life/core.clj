(ns game-of-life.core
  (:gen-class)
  (:require [game-of-life.utils :as utils]))

(defn- count-live-neighbors
  [board position]
  (count (filter #(contains? (:cells board) %) (utils/neighbors board position))))

(defn- lives-next-tick?
  [board position]
  (let [live-neighbors (count-live-neighbors board position)
        alive (contains? (:cells board) position)]
    (cond
      alive (cond
              (< live-neighbors 2) false
              (<= live-neighbors 3) true
              (> live-neighbors 3) false)
      (= 3 live-neighbors) true
      :else false)))

(defn create-board 
  "Receives a width, height and an optional set of cell positions [x y]"
  ([width height cells]
   {:width width :height height :cells cells})
  ([width height]
   {:width width :height height :cells #{}}))

(defn add-cells
  [board & positions]
  (reduce utils/add-cell board positions))

(defn remove-cells
  [board & positions]
  (reduce utils/remove-cell board positions))

(defn toggle-cell
  [board position]
  (if (contains? (:cells board) position)
    (utils/remove-cell board position)
    (utils/add-cell board position)))

(defn next-tick
  [board]
  (let [positions (for [x (range (:width board))
                        y (range (:height board))]
                    [x y])]
    (reduce (fn [next-board position]
              (if (lives-next-tick? board position)
                (utils/add-cell next-board position)
                (utils/remove-cell next-board position)))
            board positions)))

