//
//  ActivityIndicator.swift
//  iosApp
//
//  Created by Pan, Xiaoheng on 1/24/20.
//

import SwiftUI

struct ActivityIndicator: UIViewRepresentable {

    @Binding var isLoading: Bool

    func makeUIView(context: Context) -> UIActivityIndicatorView {
        return UIActivityIndicatorView()
    }

    func updateUIView(_ uiView: UIActivityIndicatorView, context: Context) {
        if self.isLoading{
            uiView.startAnimating()
        } else {
            uiView.stopAnimating()
        }
    }
}
