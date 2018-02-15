import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ActivityListItemSuchaz } from './activity-list-item-suchaz.model';
import { ActivityListItemSuchazPopupService } from './activity-list-item-suchaz-popup.service';
import { ActivityListItemSuchazService } from './activity-list-item-suchaz.service';
import { ActivityListSuchaz, ActivityListSuchazService } from '../activity-list-suchaz';
import { ItemSuchaz, ItemSuchazService } from '../item-suchaz';

@Component({
    selector: 'jhi-activity-list-item-suchaz-dialog',
    templateUrl: './activity-list-item-suchaz-dialog.component.html'
})
export class ActivityListItemSuchazDialogComponent implements OnInit {

    activityListItem: ActivityListItemSuchaz;
    isSaving: boolean;

    activitylists: ActivityListSuchaz[];

    items: ItemSuchaz[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private activityListItemService: ActivityListItemSuchazService,
        private activityListService: ActivityListSuchazService,
        private itemService: ItemSuchazService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.activityListService.query()
            .subscribe((res: HttpResponse<ActivityListSuchaz[]>) => { this.activitylists = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.itemService.query()
            .subscribe((res: HttpResponse<ItemSuchaz[]>) => { this.items = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.activityListItem.id !== undefined) {
            this.subscribeToSaveResponse(
                this.activityListItemService.update(this.activityListItem));
        } else {
            this.subscribeToSaveResponse(
                this.activityListItemService.create(this.activityListItem));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ActivityListItemSuchaz>>) {
        result.subscribe((res: HttpResponse<ActivityListItemSuchaz>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: ActivityListItemSuchaz) {
        this.eventManager.broadcast({ name: 'activityListItemListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackActivityListById(index: number, item: ActivityListSuchaz) {
        return item.id;
    }

    trackItemById(index: number, item: ItemSuchaz) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-activity-list-item-suchaz-popup',
    template: ''
})
export class ActivityListItemSuchazPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private activityListItemPopupService: ActivityListItemSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.activityListItemPopupService
                    .open(ActivityListItemSuchazDialogComponent as Component, params['id']);
            } else {
                this.activityListItemPopupService
                    .open(ActivityListItemSuchazDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
