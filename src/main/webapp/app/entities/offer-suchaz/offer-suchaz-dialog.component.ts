import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { OfferSuchaz } from './offer-suchaz.model';
import { OfferSuchazPopupService } from './offer-suchaz-popup.service';
import { OfferSuchazService } from './offer-suchaz.service';
import { ItemSuchaz, ItemSuchazService } from '../item-suchaz';

@Component({
    selector: 'jhi-offer-suchaz-dialog',
    templateUrl: './offer-suchaz-dialog.component.html'
})
export class OfferSuchazDialogComponent implements OnInit {

    offer: OfferSuchaz;
    isSaving: boolean;

    items: ItemSuchaz[];

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private offerService: OfferSuchazService,
        private itemService: ItemSuchazService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.itemService.query()
            .subscribe((res: HttpResponse<ItemSuchaz[]>) => { this.items = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.offer.id !== undefined) {
            this.subscribeToSaveResponse(
                this.offerService.update(this.offer));
        } else {
            this.subscribeToSaveResponse(
                this.offerService.create(this.offer));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<OfferSuchaz>>) {
        result.subscribe((res: HttpResponse<OfferSuchaz>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: OfferSuchaz) {
        this.eventManager.broadcast({ name: 'offerListModification', content: 'OK'});
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

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}

@Component({
    selector: 'jhi-offer-suchaz-popup',
    template: ''
})
export class OfferSuchazPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private offerPopupService: OfferSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.offerPopupService
                    .open(OfferSuchazDialogComponent as Component, params['id']);
            } else {
                this.offerPopupService
                    .open(OfferSuchazDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
