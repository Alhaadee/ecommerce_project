import './App.css';
import HomeContainer from './containers/HomeContainer';
import {QueryClient, QueryClientProvider} from 'react-query'
import { Route, Routes } from 'react-router-dom';
import NotFound from './components/NotFound';
import NavBar from './components/NavBar';
import Footer from './components/Footer';
const queryClient = new QueryClient();

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <NavBar/>
      <Routes>
        <Route path="/" element={<HomeContainer/>}/>
        <Route path="*" element={<NotFound />}/>
      </Routes>
      <Footer />
    </QueryClientProvider>
  );
}

export default App;
