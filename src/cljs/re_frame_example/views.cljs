(ns re-frame-tutorial.views
  (:require
   [clojure.string :as str]
   [re-frame.core :as rf]
   [re-frame-tutorial.subs :as subs]
   [re-frame-tutorial.events :as events]
   [reagent.core :as r]
   ))

(defn todo-create []
  (let [val (r/atom "")]
    (fn []
      [:input {:type "text"
               :value @val
               :on-change #(reset! val (-> % .-target .-value))
               :on-key-down #(when (= (.-which %) 13)
                               (let [title (-> @val str/trim)]
                                 (when (seq title)
                                   (rf/dispatch [::events/add-todo title]))
                                 (reset! val "")))}])))

(defn todo-item [{:keys [id done] :as item}]
  [:li
   [:input {:type "checkbox"
            :class "toggle"
            :checked (and done "checked")
            :on-change #(rf/dispatch [::events/toggle-todo id])}]
   [:span {:class (when done "done")}
    (:title item)]
   [:span.delete {:on-click #(rf/dispatch [::events/delete-todo id])}
    [:i.fa.fa-trash]]])

(defn todo-list [todos]
  [:ul.todos
   (for [todo todos]
     ^{:key (:id todo)}
     [todo-item todo])])

(defn main-panel []
  (let [todos (rf/subscribe [::subs/todos])]
    [:div
     [:h1 "TODO"]
     [todo-create]
     [todo-list @todos]
     ]))
