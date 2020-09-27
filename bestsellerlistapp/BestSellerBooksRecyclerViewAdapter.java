package com.codepath.bestsellerlistapp;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import androidx.recyclerview.widget.RecyclerView;

import com.codepath.bestsellerlistapp.models.BestSellerBook;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link BestSellerBook} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class BestSellerBooksRecyclerViewAdapter extends RecyclerView.Adapter<BestSellerBooksRecyclerViewAdapter.BookViewHolder> {

    private final List<BestSellerBook> books;
    private final OnListFragmentInteractionListener mListener;

    public BestSellerBooksRecyclerViewAdapter(List<BestSellerBook> items, OnListFragmentInteractionListener listener) {
        books = items;
        mListener = listener;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_best_seller_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BookViewHolder holder, int position) {
        final BestSellerBook bestSellerBook = books.get(position);
        Glide.with(holder.mView)
                .load(bestSellerBook.bookImageUrl)
                .centerInside()
                .into(holder.mBookImage);

        holder.mItem = books.get(position);
        holder.mBookTitle.setText(books.get(position).title);
        holder.mBookAuthor.setText(books.get(position).author);

        //holder.mBookImage.setImageIcon(books.get(position).bookImageUrl);
        String ranking = String.format("%d", books.get(position).rank);
        holder.mBookRanking.setText(ranking);
        holder.mBookDescription.setText(books.get(position).description);
        holder.mBookButton.setText("BUY ON AMAZON");

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onItemClick(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mBookTitle;
        public final TextView mBookAuthor;
        public final TextView mBookDescription;
        public final TextView mBookRanking;
        public final ImageView mBookImage;
        public final Button mBookButton;

        public BestSellerBook mItem;

        public BookViewHolder(View view) {
            super(view);
            mView = view;
            mBookTitle = (TextView) view.findViewById(R.id.book_title);
            mBookAuthor = (TextView) view.findViewById(R.id.book_author);

            mBookDescription = (TextView) view.findViewById(R.id.book_description);
            mBookRanking = (TextView) view.findViewById(R.id.ranking);
            mBookImage = (ImageView) view.findViewById(R.id.book_image);
            mBookButton = (Button) view.findViewById(R.id.buy_button);
        }

        @Override
        public String toString() {
            return mBookTitle.toString() + " '" + mBookAuthor.getText() + "'";
        }
    }
}
