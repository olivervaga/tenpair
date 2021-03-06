package eu.j0ntech.matchnumbers.fragment;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout.LayoutParams;
import eu.j0ntech.matchnumbers.R;
import eu.j0ntech.matchnumbers.save.SaveDetail;
import eu.j0ntech.matchnumbers.save.Saver;
import eu.j0ntech.matchnumbers.view.LoadTextView;

public class LoadDialog extends DialogFragment {

	private LinearLayout mFilenameContainer;
	private LinearLayout mButtonContainer;
	private Button mLoadButton;
	private Button mDeleteButton;
	private ProgressBar mDeleteProgress;

	private String selectedItemPath;

	private LoadDialogListener mListener;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final List<SaveDetail> saves = Saver.getAvailableSaves(getActivity());
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		if (saves != null) {
			builder.setTitle(getString(R.string.dialog_load));

			View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_load, null);

			mDeleteProgress = (ProgressBar) view.findViewById(R.id.delete_progress);
			mFilenameContainer = (LinearLayout) view.findViewById(R.id.filename_container);
			mButtonContainer = (LinearLayout) view.findViewById(R.id.button_container);
			mLoadButton = (Button) view.findViewById(R.id.load_button);
			mDeleteButton = (Button) view.findViewById(R.id.delete_button);

			createLoadList(saves);

			mLoadButton.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					if (selectedItemPath != null) {
						mListener.onLoad(selectedItemPath);
					}
				}
			});
			mDeleteButton.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					if (selectedItemPath != null) {
						mListener.onDelete(selectedItemPath);
					}
				}
			});
			builder.setView(view);
		} else {
			builder.setTitle(getString(R.string.error_no_saves));
		}
		return builder.create();
	}

	private void createLoadList(List<SaveDetail> saves) {
		mFilenameContainer.removeAllViews();
		for (SaveDetail save : saves) {
			addLoadListItem(save, mFilenameContainer);
		}
	}

	private void addLoadListItem(SaveDetail fileDetail, final LinearLayout parent) {
		final LoadTextView tv = new LoadTextView(getActivity());
		tv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		tv.setTextSize(20f);
		tv.setGravity(Gravity.CENTER);
		tv.setPadding(10, 10, 10, 10);
		String name = fileDetail.getName();
		tv.setText(name);
		tv.setTag(fileDetail.getPath());
		tv.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!tv.isSelected()) {
					tv.setSelected(true);
					if (selectedItemPath != null)
						mFilenameContainer.findViewWithTag(selectedItemPath).setSelected(false);
					selectedItemPath = (String) tv.getTag();
				} else {
					tv.setSelected(false);
					selectedItemPath = null;
				}

			}
		});
		parent.addView(tv);
	}

	public void setAsLoading() {
		mFilenameContainer.setVisibility(View.GONE);
		mButtonContainer.setVisibility(View.GONE);
		mDeleteProgress.setVisibility(View.VISIBLE);
	}

	public void setAsNormal() {
		mFilenameContainer.setVisibility(View.VISIBLE);
		mButtonContainer.setVisibility(View.VISIBLE);
		mDeleteProgress.setVisibility(View.GONE);
		selectedItemPath = null;
		final List<SaveDetail> saves = Saver.getAvailableSaves(getActivity());
		if (saves != null)
			createLoadList(saves);
		else {
			this.dismiss();
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mListener = (LoadDialogListener) activity;
	}

	public interface LoadDialogListener {
		public void onLoad(String filepath);

		public void onDelete(String filepath);
	}

}
