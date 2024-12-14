(defproject game-of-life "0.1.0-SNAPSHOT"
  :description "Conway's Game of Life"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [cljfx "1.9.2"]]
  :main game-of-life.ui
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :injections [(javafx.application.Platform/exit)]
                     :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})

