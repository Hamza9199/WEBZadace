import React, { useEffect, useState } from "react";
import { Bar, Doughnut } from "react-chartjs-2";
import {
  Chart as ChartJS,
  ArcElement,
  BarElement,
  CategoryScale,
  LinearScale,
  Tooltip,
  Legend,
} from "chart.js";

ChartJS.register(ArcElement, BarElement, CategoryScale, LinearScale, Tooltip, Legend);

const DashboardChart = () => {
  const [averageRating, setAverageRating] = useState(null);
  const [totalJobs, setTotalJobs] = useState(null);

  useEffect(() => {
    const fetchStatistics = async () => {
      try {
        const [ratingResponse, jobsResponse] = await Promise.all([
          fetch("http://localhost:8080/api/statistics/average-rating"),
          fetch("http://localhost:8080/api/statistics/total-jobs"),
        ]);

        if (ratingResponse.ok && jobsResponse.ok) {
          const ratingData = await ratingResponse.json();
          const jobsData = await jobsResponse.json();
          setAverageRating(ratingData);
          setTotalJobs(jobsData);
        } else {
          console.error("Failed to fetch statistics");
        }
      } catch (error) {
        console.error("Error fetching statistics:", error);
      }
    };

    fetchStatistics();
  }, []);

  if (averageRating === null || totalJobs === null) {
    return <p>Loading...</p>;
  }

  const barData = {
    labels: ["Prosečna Ocena", "Ukupno Poslova"],
    datasets: [
      {
        label: "Statistika",
        data: [averageRating, totalJobs],
        backgroundColor: ["#3b82f6", "#22c55e"],
      },
    ],
  };

  const doughnutData = {
    labels: ["Prosečna Ocena", "Ukupno Poslova"],
    datasets: [
      {
        label: "Statistika",
        data: [averageRating, totalJobs],
        backgroundColor: ["#f87171", "#34d399"],
      },
    ],
  };

  return (
    <div className="charts-container">
      <div className="chart">
        <h3>Bar Grafikon</h3>
        <Bar data={barData} />
      </div>
      <div className="chart">
        <h3>Doughnut Grafikon</h3>
        <Doughnut data={doughnutData} />
      </div>
    </div>
  );
};

export default DashboardChart;
