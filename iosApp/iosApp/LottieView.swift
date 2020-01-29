//
//  LottieView.swift
//  iosApp
//
//  Created by Pan, Xiaoheng on 1/27/20.
//

import SwiftUI
import Lottie

struct LottieView: UIViewRepresentable {
    private let animationView = AnimationView()
    private let filename = "wave-loading"
    @Binding var isLoading: Bool

    func makeUIView(context: UIViewRepresentableContext<LottieView>) -> AnimationView {
        let animationView = AnimationView()
        let animation = Animation.named(filename)
        animationView.animation = animation
        animationView.loopMode = .loop
        return animationView
    }

    func updateUIView(_ uiView: AnimationView, context: UIViewRepresentableContext<LottieView>) {
        isLoading ? uiView.play() : uiView.stop()
    }
}
