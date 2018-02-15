/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { StoreImageSuchazDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/store-image-suchaz/store-image-suchaz-delete-dialog.component';
import { StoreImageSuchazService } from '../../../../../../main/webapp/app/entities/store-image-suchaz/store-image-suchaz.service';

describe('Component Tests', () => {

    describe('StoreImageSuchaz Management Delete Component', () => {
        let comp: StoreImageSuchazDeleteDialogComponent;
        let fixture: ComponentFixture<StoreImageSuchazDeleteDialogComponent>;
        let service: StoreImageSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [StoreImageSuchazDeleteDialogComponent],
                providers: [
                    StoreImageSuchazService
                ]
            })
            .overrideTemplate(StoreImageSuchazDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(StoreImageSuchazDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StoreImageSuchazService);
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
