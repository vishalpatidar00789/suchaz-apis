import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ItemReviewSuchaz } from './item-review-suchaz.model';
import { ItemReviewSuchazPopupService } from './item-review-suchaz-popup.service';
import { ItemReviewSuchazService } from './item-review-suchaz.service';
import { ItemSuchaz, ItemSuchazService } from '../item-suchaz';

@Component({
    selector: 'jhi-item-review-suchaz-dialog',
    templateUrl: './item-review-suchaz-dialog.component.html'
})
export class ItemReviewSuchazDialogComponent implements OnInit {

    itemReview: ItemReviewSuchaz;
    isSaving: boolean;

    items: ItemSuchaz[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private itemReviewService: ItemReviewSuchazService,
        private itemService: ItemSuchazService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.itemService.query()
            .subscribe((res: HttpResponse<ItemSuchaz[]>) => { this.items = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.itemReview.id !== undefined) {
            this.subscribeToSaveResponse(
                this.itemReviewService.update(this.itemReview));
        } else {
            this.subscribeToSaveResponse(
                this.itemReviewService.create(this.itemReview));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ItemReviewSuchaz>>) {
        result.subscribe((res: HttpResponse<ItemReviewSuchaz>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: ItemReviewSuchaz) {
        this.eventManager.broadcast({ name: 'itemReviewListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackItemById(index: number, item: ItemSuchaz) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-item-review-suchaz-popup',
    template: ''
})
export class ItemReviewSuchazPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private itemReviewPopupService: ItemReviewSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.itemReviewPopupService
                    .open(ItemReviewSuchazDialogComponent as Component, params['id']);
            } else {
                this.itemReviewPopupService
                    .open(ItemReviewSuchazDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
