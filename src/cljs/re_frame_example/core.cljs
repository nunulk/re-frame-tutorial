(ns re-frame-tutorial.core
  (:require
   [reagent.core :as r]
   [re-frame.core :as rf]
   [re-frame-tutorial.events :as events]
   [re-frame-tutorial.views :as views]
   [re-frame-tutorial.config :as config]
   ))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (rf/clear-subscription-cache!)
  (r/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (rf/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
