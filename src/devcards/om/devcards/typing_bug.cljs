(ns om.devcards.typing-bug
  (:require [devcards.core :refer-macros [defcard deftest dom-node]]
            [om.dom :as dom]
            [om.core :as om]))

(def app-state (atom {}))

(defn text-view
  [data owner]
  (reify
    om/IInitState
    (init-state [_]
      {:text ""})
    om/IDisplayName
    (display-name [_] "TextView")
    om/IRenderState
    (render-state [_ state]
      (dom/input #js {:type "text"
                      :value (:text state)
                      :onChange (fn [evt]
                                  (let [val (.. evt -target -value)]
                                    (om/set-state! owner :text val)))}))))

(defcard text-field-bug
  "Test that typing in input field doesn't lose characters."
  (dom-node
   (fn [_ node]
     (om/root text-view
              app-state
              {:target node}))))

