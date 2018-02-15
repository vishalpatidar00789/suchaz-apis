import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { StoreSuchaz } from './store-suchaz.model';
import { StoreSuchazPopupService } from './store-suchaz-popup.service';
import { StoreSuchazService } from './store-suchaz.service';
import { ItemSuchaz, ItemSuchazService } from '../item-suchaz';

@Component({
    selector: 'jhi-store-suchaz-dialog',
    templateUrl: './store-suchaz-dialog.component.html'
})
export class StoreSuchazDialogComponent implements OnInit {

    store: StoreSuchaz;
    isSaving: boolean;

    items: ItemSuchaz[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private storeService: StoreSuchazService,
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
        if (this.store.id !== undefined) {
            this.subscribeToSaveResponse(
                this.storeService.update(this.store));
        } else {
            this.subscribeToSaveResponse(
                this.storeService.create(this.store));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<StoreSuchaz>>) {
        result.subscribe((res: HttpResponse<StoreSuchaz>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: StoreSuchaz) {
        this.eventManager.broadcast({ name: 'storeListModification', content: 'OK'});
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
    selector: 'jhi-store-suchaz-popup',
    template: ''
})
export class StoreSuchazPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private storePopupService: StoreSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.storePopupService
                    .open(StoreSuchazDialogComponent as Component, params['id']);
            } else {
                this.storePopupService
                    .open(StoreSuchazDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
