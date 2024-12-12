(ns game-of-life.utils)

(defn in-board?
  "Evaluate if a position is inside a board"
  [board [x y]]
  (and (< x (:width board)) (< y (:height board)) (>= x 0) (>= y 0)))

(defn add-cell
  [board position]
  (if (in-board? board position)
    (update-in board [:cells] conj position)
    board))

(defn add-cells
  [board & positions]
  (reduce add-cell board positions))

(defn remove-cell
  [board position]
  (update-in board [:cells] disj position))

(defn remove-cells
  [board & positions]
  (reduce remove-cell board positions))

(defn neighbors
  "Returns a vector with the valid adjacent positions for a given position"
  [board [x y]]
  (filter #(in-board? board [(first %) (second %)])
          [[(inc x) y]
           [(dec x) y]
           [x (inc y)]
           [x (dec y)]
           [(inc x) (inc y)]
           [(inc x) (dec y)]
           [(dec x) (inc y)]
           [(dec x) (dec y)]]))

