import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ItemAttributeTypeSuchaz } from './item-attribute-type-suchaz.model';
import { ItemAttributeTypeSuchazPopupService } from './item-attribute-type-suchaz-popup.service';
import { ItemAttributeTypeSuchazService } from './item-attribute-type-suchaz.service';
import { CategorySuchaz, CategorySuchazService } from '../category-suchaz';

@Component({
    selector: 'jhi-item-attribute-type-suchaz-dialog',
    templateUrl: './item-attribute-type-suchaz-dialog.component.html'
})
export class ItemAttributeTypeSuchazDialogComponent implements OnInit {

    itemAttributeType: ItemAttributeTypeSuchaz;
    isSaving: boolean;

    categories: CategorySuchaz[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private itemAttributeTypeService: ItemAttributeTypeSuchazService,
        private categoryService: CategorySuchazService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.categoryService.query()
            .subscribe((res: HttpResponse<CategorySuchaz[]>) => { this.categories = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.itemAttributeType.id !== undefined) {
            this.subscribeToSaveResponse(
                this.itemAttributeTypeService.update(this.itemAttributeType));
        } else {
            this.subscribeToSaveResponse(
                this.itemAttributeTypeService.create(this.itemAttributeType));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ItemAttributeTypeSuchaz>>) {
        result.subscribe((res: HttpResponse<ItemAttributeTypeSuchaz>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: ItemAttributeTypeSuchaz) {
        this.eventManager.broadcast({ name: 'itemAttributeTypeListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackCategoryById(index: number, item: CategorySuchaz) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-item-attribute-type-suchaz-popup',
    template: ''
})
export class ItemAttributeTypeSuchazPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private itemAttributeTypePopupService: ItemAttributeTypeSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.itemAttributeTypePopupService
                    .open(ItemAttributeTypeSuchazDialogComponent as Component, params['id']);
            } else {
                this.itemAttributeTypePopupService
                    .open(ItemAttributeTypeSuchazDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
