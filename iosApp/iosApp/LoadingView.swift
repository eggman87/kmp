//
//  LoadingView.swift
//  iosApp
//
//  Created by Pan, Xiaoheng on 1/27/20.
//

import SwiftUI

struct LoadingView<Content>: View where Content: View {

    @Binding var isLoading: Bool
    var content: () -> Content

    var body: some View {
        GeometryReader { geometry in
            ZStack(alignment: .center) {
                self.content()
                    .disabled(self.isLoading)
                    .blur(radius: self.isLoading ? 3 : 0)

                VStack {
//                    Text("Loading ...")
//                    ActivityIndicator(isLoading: self.$isLoading)
                    LottieView(isLoading: self.$isLoading)
                }
                .frame(width: 200, height: 200)
//                .background(Color.secondary.colorInvert())
                .foregroundColor(Color.primary)
                .cornerRadius(20)
                .opacity(self.isLoading ? 1 : 0)

            }
        }
    }
}
