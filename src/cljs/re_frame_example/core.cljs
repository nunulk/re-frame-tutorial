(ns re-frame-example.core
  (:require
   [reagent.core :as r]
   [re-frame.core :as rf]
   [re-frame-example.events :as events]
   [re-frame-example.views :as views]
   [re-frame-example.config :as config]
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
