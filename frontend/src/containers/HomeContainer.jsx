import React from 'react'
import {useQuery, useQueryClient} from '@tanstack/react-query'
import axios from 'axios';
import ProductList from '../components/ProductList';
import '../styles/Hero.css'
import monitor from '../assets/monitor.jpg'



function HomeContainer() {

  
 const queryClient = useQueryClient()
  
  const fetchProducts = () => {
    return axios.get('http://localhost:8080/api/v1/products')
  }

  const fetchFakeProducts = () => {
    return axios.get('https://fakestoreapi.com/products')
  }

  const {data: fakeProducts, isLoading, isError, error} = useQuery(["fake-products"], fetchFakeProducts)

  const products = useQuery(["products"], fetchProducts)


  if(isError){
    return <h1>Error: {error.message}</h1>
  }

  
  return (
    <>

    <div className='hero'>
        <div className='description'>
            <h2 className='hero-title'>Airpods Max</h2>
            <button>Find out more</button>
        </div>
        
    </div>
    <div className='categories-container monitor'>
      <div className='category-card'>
        <img src={monitor} alt="monitor" />
        <h2>Shop Accesories</h2>     
      </div>
      <div className='category-card accessory'>
              <h2 >Shop Monitors</h2> 
      </div>
    </div>

    <h2>Popular Items</h2>
    
    {isLoading ? <h1>Loading...</h1>:<ProductList fakeProducts={fakeProducts?.data.slice(8,12)}/>}
    
    </>
  )

  // maybe move the fetch request to the products list
}

export default HomeContainer