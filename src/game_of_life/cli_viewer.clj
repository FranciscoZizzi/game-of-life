(ns game-of-life.cli-viewer
  (:require [game-of-life.core :refer [create-board next-tick add-cells remove-cells]]))

(defn- show-board
  [board]
  (println (repeat (:width board) \_))
  (loop [x 0
        y 0
        string ""]
    (if (>= y (:height board))
      (println (repeat (:width board) \_))
      (let [cells (:cells board)
            leading-string (if (= x 0)
                             "|"
                             "")
            trailing-string (if (= x (- (:width board) 1))
                              "|"
                              " ")
            next-string (if (contains? cells [x y])
                         (str string leading-string "o" trailing-string)
                         (str string leading-string " " trailing-string))
            next-x (mod (inc x) (:width board))
            next-y (if (= 0 next-x) (inc y) y)]
        (if (= 0 next-x)
          (do
            (println next-string)
            (recur next-x next-y ""))
          (recur next-x next-y next-string))))))

(defn- parse-to-vector 
  [input]
  (let [pattern #"[\(\[](-?\d+)\s(-?\d+)[\)\]]"]
    (if-let [[_ x y] (re-matches pattern input)]
      [(Integer. x) (Integer. y)]
      (throw (IllegalArgumentException. "Invalid input format")))))

(defn -main 
  []
  (loop [board (create-board 10 10)
         message ""]
    (if (= message "")
      (show-board board)
      (println message))
    (let [input (read-line)]
      (cond 
        (or (= input "e") (= input "q")) nil
        (re-find #"^(-?\d+)\s(-?\d+)$" input) (recur (create-board (first (parse-to-vector (str "[" input "]"))) (second (parse-to-vector (str "[" input "]")))) "")
        (= input "") (recur (next-tick board) "")
        (re-find #"^\[(-?\d+)\s(-?\d+)\]$" input) 
          (recur (add-cells board (parse-to-vector input)) "")
        (re-find #"^\((-?\d+)\s(-?\d+)\)$" input)
          (recur (remove-cells board (parse-to-vector input)) "")
        (= input "h")
            (recur board "exit: e or q\nnew board: width height\nnext step: press enter\nadd cell: [x y]\nremove cell: (x y)\nhelp: h")
        :else
            (recur board "Invalid input, type h for help")))))

