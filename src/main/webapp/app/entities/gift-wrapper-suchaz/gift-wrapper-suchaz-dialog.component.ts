import { Component, OnInit, OnDestroy, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { GiftWrapperSuchaz } from './gift-wrapper-suchaz.model';
import { GiftWrapperSuchazPopupService } from './gift-wrapper-suchaz-popup.service';
import { GiftWrapperSuchazService } from './gift-wrapper-suchaz.service';

@Component({
    selector: 'jhi-gift-wrapper-suchaz-dialog',
    templateUrl: './gift-wrapper-suchaz-dialog.component.html'
})
export class GiftWrapperSuchazDialogComponent implements OnInit {

    giftWrapper: GiftWrapperSuchaz;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private giftWrapperService: GiftWrapperSuchazService,
        private elementRef: ElementRef,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
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

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.giftWrapper, this.elementRef, field, fieldContentType, idInput);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.giftWrapper.id !== undefined) {
            this.subscribeToSaveResponse(
                this.giftWrapperService.update(this.giftWrapper));
        } else {
            this.subscribeToSaveResponse(
                this.giftWrapperService.create(this.giftWrapper));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<GiftWrapperSuchaz>>) {
        result.subscribe((res: HttpResponse<GiftWrapperSuchaz>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: GiftWrapperSuchaz) {
        this.eventManager.broadcast({ name: 'giftWrapperListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-gift-wrapper-suchaz-popup',
    template: ''
})
export class GiftWrapperSuchazPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private giftWrapperPopupService: GiftWrapperSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.giftWrapperPopupService
                    .open(GiftWrapperSuchazDialogComponent as Component, params['id']);
            } else {
                this.giftWrapperPopupService
                    .open(GiftWrapperSuchazDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
