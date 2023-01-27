import './App.css';
import HomeContainer from './containers/HomeContainer';
import {QueryClient, QueryClientProvider} from '@tanstack/react-query'
import { Route, Routes } from 'react-router-dom';
import NotFound from './components/NotFound';
import NavBar from './components/NavBar';
import Footer from './components/Footer';
import { ReactQueryDevtools } from '@tanstack/react-query-devtools'
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
      <ReactQueryDevtools/>
    </QueryClientProvider>
  );
}

export default App;
