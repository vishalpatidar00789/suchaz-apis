/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { ItemCommonAttributeSuchazDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/item-common-attribute-suchaz/item-common-attribute-suchaz-delete-dialog.component';
import { ItemCommonAttributeSuchazService } from '../../../../../../main/webapp/app/entities/item-common-attribute-suchaz/item-common-attribute-suchaz.service';

describe('Component Tests', () => {

    describe('ItemCommonAttributeSuchaz Management Delete Component', () => {
        let comp: ItemCommonAttributeSuchazDeleteDialogComponent;
        let fixture: ComponentFixture<ItemCommonAttributeSuchazDeleteDialogComponent>;
        let service: ItemCommonAttributeSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [ItemCommonAttributeSuchazDeleteDialogComponent],
                providers: [
                    ItemCommonAttributeSuchazService
                ]
            })
            .overrideTemplate(ItemCommonAttributeSuchazDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ItemCommonAttributeSuchazDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ItemCommonAttributeSuchazService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
