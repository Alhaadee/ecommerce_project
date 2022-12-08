import './App.css';
import HomeContainer from './containers/HomeContainer';
import {QueryClient, QueryClientProvider} from 'react-query'
const queryClient = new QueryClient();

function App() {
  return (
    <QueryClientProvider client={queryClient}>
    <div className="App">
      <HomeContainer/>
    </div>
    </QueryClientProvider>
  );
}

export default App;
