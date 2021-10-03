import SwiftUI
import shared

class ViewModel: ObservableObject {
    @Published var user: String?
    @Published var gravity: String?
    @Published var heading: String?

    let sensors: Sensors = {
        let sensors = Sensors()
        sensors.start()
        return sensors
    }()

    init() {
        self.sensors.data.watch { data in
            DispatchQueue.main.async {
                guard let data = data else { return }
                self.user = "\(data.component2().x),\(data.component2().y),\(data.component2().z)"
                self.gravity = "\(data.component3()?.x ?? 0),\(data.component3()?.y ?? 0),\(data.component3()?.z ?? 0)"
                self.heading = "\(data.component1())'"
            }
        }
    }
}

struct ContentView: View {
    @ObservedObject var viewModel = ViewModel()

	var body: some View {
        VStack {
            Text("Sensors")
            Text(viewModel.user ?? "")
            Text(viewModel.gravity ?? "")
            Text(viewModel.heading ?? "")
        }
	}
}

//struct ContentView_Previews: PreviewProvider {
//	static var previews: some View {
//		ContentView()
//	}
//}
