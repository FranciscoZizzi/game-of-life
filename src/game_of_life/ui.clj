(ns game-of-life.ui
  (:gen-class)
  (:require [cljfx.api :as fx]
            [game-of-life.core :as core]))

(defonce state (atom {:title "Conway's Game of Life" 
                      :board (core/create-board 50 50 #{[10 10] [10 9] [10 11] [9 10] [11 11]})}))

(defonce simulation (atom nil))

(defn tick
  []
  (swap! state update :board core/next-tick))

(defn stop-simulation
  []
  (when @simulation
    (println "Simulation stopping")
    (future-cancel @simulation)
    (reset! simulation nil)))

(defn start-simulation 
  []
  (when (nil? @simulation)
    (println "Simulation starting")
    (reset! simulation
            (future
              (loop []
                (tick)
                (Thread/sleep 1000)
                (when-not (future-cancelled? @simulation)
                  (recur)))))))

(defn render-grid 
  [board]
  {:fx/type :grid-pane
   :children (for [x (range (:width board))
                   y (range (:height board))]
               {:fx/type :rectangle
                :grid-pane/row y
                :grid-pane/column x
                :width 20
                :height 20
                :fill (if (contains? (:cells board) [x y]) :white :black)
                :on-mouse-clicked {:event/type :event/cell-click
                                   :x x
                                   :y y}})})

(defn render-controls
  []
  {:fx/type :v-box
   :spacing 5
   :children [{:fx/type :label
               :text "The game"}
              {:fx/type :button
               :text "Start"
               :on-action (fn [_] (start-simulation))}
              {:fx/type :button
               :text "Stop"
               :on-action (fn [_] (stop-simulation))}]})

(defn handle-click
  [[x y]]
  (swap! state update :board core/toggle-cell [x y]))

(defn event-handler 
  [event]
  (case (:event/type event)
    :event/cell-click (handle-click [(:x event) (:y event)])
    nil))


(defn view 
  [state]
  {:fx/type :stage
   :showing true
   :title (:title state)
   :scene {:fx/type :scene
           :root {:fx/type :h-box
                  :spacing 10
                  :children [(render-grid (:board state))
                             (render-controls)]}}
   :on-close-request (fn [_] (System/exit 0))})

(defn -main 
  [& args]
  (fx/mount-renderer
    state
    (fx/create-renderer
      :middleware (fx/wrap-map-desc view)
      :opts {:fx.opt/map-event-handler event-handler})))

