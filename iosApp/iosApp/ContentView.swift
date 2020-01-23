//
//  ContentView.swift
//  iosApp
//
//  Created by Pan, Xiaoheng on 1/21/20.
//

import SwiftUI
import app

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

struct ContentView: View {
    
    @ObservedObject var todos = TodoData()
    
    lazy var todoViewModel = TodosViewModel.init(todoData:self.todos)
    
    init() {
        todoViewModel.loadTodos()
    }
    
    var body: some View {
        NavigationView {
            VStack {
                ActivityIndicator(isLoading: self.$todos.isLoading)
                List (todos.todoList, id: \.title) { todo in
                    Text("\(todo.title)")
                }.navigationBarTitle("Todo Lists")
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
