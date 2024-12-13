(ns game-of-life.test-utils
  (:require [game-of-life.core :as core]
            [clojure.test :refer [is]]))

(defn- board-to-matrix
  [board]
  (let [cells (:cells board)]
    (loop [x 0
           y 0
           matrix []]
      (if (>= y (:height board))
        matrix
        (let [row (or (get matrix y []) [])
              next-matrix (assoc matrix y (if (contains? cells [x y])
                             (conj row 'o)
                             (conj row '-)))
             next-x (mod (inc x) (:width board))
             next-y (if (zero? next-x) (inc y) y)]
         (recur next-x next-y next-matrix)))
     )))

(defn- matrix-to-board
  [matrix]
  (loop [x 0
         y 0
         cells #{}
         width (count (get matrix 0))
         height (count matrix)]
    (if (>= y height)
      (core/create-board width height cells)
      (let [new-cells (if (= (get-in matrix [y x]) 'o)
                        (conj cells [x y])
                        cells)
            next-x (mod (inc x) width)
            next-y (if (= next-x 0) (inc y) y)]
        (recur next-x next-y new-cells width height)))))

(defn expect-next-state
  [current expected]
  (is (= (board-to-matrix (core/next-tick (matrix-to-board current))) expected)))

